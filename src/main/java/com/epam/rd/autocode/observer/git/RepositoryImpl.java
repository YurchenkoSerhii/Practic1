package com.epam.rd.autocode.observer.git;

import java.util.*;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {
    private final Map<String, List<WebHook>> webHooks = new HashMap<>();
    private final Map<String, Set<Commit>> mergedCommits = new HashMap<>();

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.computeIfAbsent(webHook.branch(), k -> new ArrayList<>()).add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        List<WebHook> hooks = webHooks.getOrDefault(branch, new ArrayList<>());
        Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));
        hooks.forEach(hook -> hook.onEvent(event));
        return commit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        List<Commit> commits = new ArrayList<>();
        List<WebHook> sourceHooks = webHooks.getOrDefault(sourceBranch, new ArrayList<>());
        for (WebHook hook : sourceHooks) {
            commits.addAll(hook.caughtEvents().stream()
                    .filter(e -> e.type() == Event.Type.COMMIT)
                    .flatMap(e -> e.commits().stream()).collect(Collectors.toList()));

        }

        // Фильтруем коммиты, которые уже были слиты в целевую ветку
        Set<Commit> targetMergedCommits = mergedCommits.computeIfAbsent(targetBranch, k -> new HashSet<>());
        List<Commit> newCommits = new ArrayList<>();
        for (Commit commit : commits) {
            if (!targetMergedCommits.contains(commit)) {
                newCommits.add(commit);
                targetMergedCommits.add(commit);
            }
        }

        // Создаем событие слияния только если есть новые коммиты
        if (!newCommits.isEmpty()) {
            Event event = new Event(Event.Type.MERGE, targetBranch, newCommits);
            List<WebHook> targetHooks = webHooks.getOrDefault(targetBranch, new ArrayList<>());
            targetHooks.forEach(hook -> hook.onEvent(event));
        }
    }
}

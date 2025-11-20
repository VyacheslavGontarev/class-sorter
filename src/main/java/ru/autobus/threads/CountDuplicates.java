package ru.autobus.threads;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

import java.util.concurrent.*;

public class CountDuplicates {

    public static long count(MyArrayList<Autobus> list, Autobus target) {
        if (list == null || target == null || list.isEmpty()) return 0;

        int threads = Math.max(2, Math.min(Runtime.getRuntime().availableProcessors(), list.size()));

        try (ExecutorService pool = Executors.newFixedThreadPool(threads)) {

            @SuppressWarnings("unchecked")
            Future<Long>[] results = new Future[threads];
            int chunkSize = (list.size() + threads - 1) / threads;

            for (int t = 0; t < threads; t++) {
                int start = t * chunkSize;
                int end = Math.min(start + chunkSize, list.size());

                results[t] = pool.submit(() -> {
                    long count = 0;
                    for (int i = start; i < end; i++) {
                        if (list.get(i).equals(target)) count++;
                    }
                    return count;
                });
            }

            long total = 0;
            for (Future<Long> f : results) {
                try {
                    total += f.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            return total;
        }
    }
}


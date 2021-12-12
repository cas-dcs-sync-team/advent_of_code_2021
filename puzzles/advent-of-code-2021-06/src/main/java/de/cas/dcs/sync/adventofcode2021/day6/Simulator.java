package de.cas.dcs.sync.adventofcode2021.day6;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Simulator {

  Map<String, BigInteger> cache = new ConcurrentHashMap<>();

  public BigInteger simulate(List<Fish> startFishs)
      throws ExecutionException, InterruptedException {
    AtomicReference<BigInteger> simulatedFishs = new AtomicReference<>(new BigInteger("0"));
    Queue<Fish> fishsToSimulate = new LinkedList<>(startFishs);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    List<Future<Void>> futures = new ArrayList<>();

    while (!fishsToSimulate.isEmpty()) {
      Fish fish = fishsToSimulate.poll();

      futures.add(
          executorService.submit(
              () -> {
                simulatedFishs.accumulateAndGet(simulateFish(fish), BigInteger::add);
                return null;
              }));
    }

    executorService.shutdown();

    for (Future<Void> future : futures) {
      future.get();
    }

    return simulatedFishs.get();
  }

  private BigInteger simulateFish(Fish fish) {
    BigInteger simulatedFishs = new BigInteger("1"); // counts itself
    int days = fish.startDaysLeft();
    int daysUntilReproduction = fish.daysUntilReproduction();

    if (daysUntilReproduction < 6) {
      days -= (daysUntilReproduction + 1);
      simulatedFishs = simulatedFishs.add(simulateFish(new Fish(6, days - 2)));
    }

    BigInteger cachedValue = cache.get(days + "_" + daysUntilReproduction);

    if (cachedValue != null) {
      simulatedFishs = simulatedFishs.add(cachedValue);
    } else {
      if (days >= 7) {
        BigInteger count = new BigInteger("0");
        int fishesToProduce = days / 7;
        for (int i = 0; i < fishesToProduce; i++) {
          count = count.add(simulateFish(new Fish(6, days - (7 * (i + 1)) - 2)));
        }
        simulatedFishs = simulatedFishs.add(count);
        cache.put(days + "_" + daysUntilReproduction, count);
      }
    }

    return simulatedFishs;
  }
}

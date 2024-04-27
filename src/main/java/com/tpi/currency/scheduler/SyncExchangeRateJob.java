package com.tpi.currency.scheduler;

import com.tpi.currency.service.SyncExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @created 28/04/2024 - 00:04
 * @project currency
 * @author: thainguyen
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SyncExchangeRateJob {
  private final SyncExchangeRateService syncExchangeRateService;

  @Scheduled(cron = "0 * * * * ?", zone = "Asia/Ho_Chi_Minh")
  @SchedulerLock(
      name = "task_scheduler_sync_exchange_rate",
      lockAtLeastFor = "PT1M",
      lockAtMostFor = "PT5M")
  public void synExchangeRateJob() {
    syncExchangeRateService.execution();
  }
}

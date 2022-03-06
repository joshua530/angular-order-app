import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { Alert } from 'src/app/types/Alert';

@Component({
  selector: 'app-alerts-container',
  templateUrl: './alerts-container.component.html',
  styleUrls: ['./alerts-container.component.css'],
})
export class AlertsContainerComponent implements OnInit {
  alerts: Array<Alert> = [];

  constructor(private notifier: NotificationService) {
    this.notifier
      .onNotificationCreation()
      .subscribe((alert) => this.alerts.push(alert));
  }

  ngOnInit(): void {}
}

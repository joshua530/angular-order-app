import { Component, OnInit, Input } from '@angular/core';
import { Alert } from 'src/app/types/Alert';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
})
export class AlertComponent implements OnInit {
  @Input() alert: Alert;

  constructor() {}

  ngOnInit(): void {
    // each alert will have an id of #alert-{this.id}
    // remove each alert after 3 seconds
    setTimeout(() => {
      const currentAlert = document.getElementById(
        `alert-${this.alert.alertId}`
      );
      if (currentAlert) currentAlert.remove();
    }, 3000);
  }
}

import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Alert } from '../types/Alert';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  subject: Subject<Alert> = new Subject();
  alertCount: number = 0;

  constructor() {}

  onNotificationCreation(): Observable<Alert> {
    return this.subject.asObservable();
  }

  notify(message: string, type: string): void {
    const alert = { message, type, alertId: this.alertCount };
    this.subject.next(alert);
    ++this.alertCount;
  }
}

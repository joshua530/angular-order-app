import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/services/notification.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  username: string;
  password: string;

  constructor(
    private userService: UserService,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {}

  authenticateAdmin() {
    if (!this.username) {
      this.notificationService.notify('Username cannot be empty', 'danger');
      return;
    }
    if (!this.password) {
      this.notificationService.notify('Password cannot be empty', 'danger');
      return;
    }

    this.userService
      .authenticateUser(this.username, this.password)
      .subscribe((authToken) => {
        if (authToken.token === null)
          this.notificationService.notify(
            'Invalid username or password',
            'danger'
          );
        else {
          this.userService.login(authToken.token);
          this.router.navigate(['/admin']);
        }
      });
  }
}

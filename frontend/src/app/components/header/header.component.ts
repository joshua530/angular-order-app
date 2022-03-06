import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  isAdminPage: boolean = false;
  isLoggedIn: boolean = false;

  constructor(private userService: UserService, private router: Router) {
    this.userService.onAuthChange().subscribe((type) => {
      if (type === 'logged-out') {
        this.isLoggedIn = false;
        this.isAdminPage = false;
      } else if (type === 'logged-in') {
        this.isLoggedIn = true;
        this.isAdminPage = true;
      }
    });
  }

  ngOnInit(): void {
    const url = new URL(window.location.href);
    const isAdminRoute = url.pathname.match(/^\/admin.*$/);

    // admin user links
    if (isAdminRoute) {
      this.isAdminPage = true;

      this.userService.isAuthenticated().subscribe((authToken) => {
        if (authToken.token === null) this.isLoggedIn = false;
        else this.isLoggedIn = true;
      });
    }
  }

  logoutAdmin() {
    this.userService.logout();
    this.isLoggedIn = false;
    this.router.navigate(['/admin/login']);
  }
}

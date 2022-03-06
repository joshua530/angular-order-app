import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService implements CanActivate {
  constructor(private router: Router, private userService: UserService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    const promise = new Promise<boolean>((resolve, reject) => {
      this.userService.isAuthenticated().subscribe((authToken) => {
        if (authToken.token != null) {
          resolve(true);
        } else {
          this.router.navigate(['/admin/login']);
          reject(false);
        }
      });
    });
    return promise;
  }
}

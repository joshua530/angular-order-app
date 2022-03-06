import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { OrdersComponent } from './pages/orders/orders.component';

import { ProductInfoComponent } from './pages/product-info/product-info.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { PlaceOrderFormComponent } from './components/place-order-form/place-order-form.component';
import { AlertComponent } from './components/alert/alert.component';
import { AlertsContainerComponent } from './components/alerts-container/alerts-container.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home.component';
import { AdminLoginComponent } from './pages/admin-login/admin-login.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AuthenticationService } from './services/authentication.service';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'products/:id', component: ProductInfoComponent },
  {
    path: 'admin',
    component: AdminHomeComponent,
    canActivate: [AuthenticationService],
  },
  { path: 'admin/login', component: AdminLoginComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    OrdersComponent,
    ProductInfoComponent,
    PageNotFoundComponent,
    PlaceOrderFormComponent,
    AlertComponent,
    AlertsContainerComponent,
    AdminHomeComponent,
    AdminLoginComponent,
    LoginFormComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent],
})
export class AppModule {}

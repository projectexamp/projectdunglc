import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// import Http module
import { HttpModule } from '@angular/http';

// import ReactiveFormsModule for reactive form
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';  //<<<< import it here
// import module for Routing.
import { RouterModule } from '@angular/router';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { LoginService } from './services/login.service';
import { DataTablesModule } from 'angular-datatables';
import { AuthGaurdService } from './services/auth-gaurd.service';
import { RoleComponent } from './role/role.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './classes/token-interceptor';
import { LogoutComponent } from './logout/logout.component';
import { FunctionComponent } from './function/function.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import {TableModule} from 'primeng/table';
import { UserComponent } from './user/user.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HomeComponent,
    RoleComponent,
    FunctionComponent,
    UserComponent

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    DataTablesModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    TableModule,
    NgMultiSelectDropDownModule.forRoot(),
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    RouterModule.forRoot([
      {
        path: '',
        component: LoginComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'role',
        component: RoleComponent,
        canActivate: [AuthGaurdService]
      },
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGaurdService]
      },
      {
        path: 'logout',
        component: LogoutComponent,
        canActivate: [AuthGaurdService]
      },
      {
        path: 'function',
        component: FunctionComponent,
        canActivate: [AuthGaurdService]
      },
      {
        path: 'user',
        component: UserComponent,
        canActivate: [AuthGaurdService]
      },
    ])

  ],
  providers: [
    LoginService,
    AuthGaurdService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

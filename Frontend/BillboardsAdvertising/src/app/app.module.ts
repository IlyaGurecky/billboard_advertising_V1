import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './components/admin/admin.component';
import { DeviceOwnerComponent } from './components/device-owner/device-owner.component';
import { AdvertiserComponent } from './components/advertiser/advertiser.component';
import { SignInComponent } from './components/sign-in/sign-in.component';

const appRoutes: Routes = [
  {path: 'admin', component: AdminComponent},
  {path: 'owner', component: DeviceOwnerComponent},
  {path: 'advertiser', component: AdvertiserComponent},
  {path: 'signIn', component: SignInComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    DeviceOwnerComponent,
    AdvertiserComponent,
    SignInComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

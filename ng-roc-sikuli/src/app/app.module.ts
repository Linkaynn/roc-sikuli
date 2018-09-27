import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TopNavComponent} from "./components/top-nav/top-nav.component";
import {ConsoleOutputComponent} from './components/console-output/console-output.component';
import {StatusService} from "./services/status.service";
import {ServicesCore} from "./helpers/base-service/service-core-provider";
import {BaseService} from "./helpers/base-service/base-service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    TopNavComponent,
    ConsoleOutputComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    StatusService,
    ServicesCore,
    BaseService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TopNavComponent} from "./components/top-nav/top-nav.component";
import {RocService} from "./services/roc.service";
import {ServicesCore} from "./helpers/base-service/service-core-provider";
import {BaseService} from "./helpers/base-service/base-service";
import {HttpClientModule} from "@angular/common/http";
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {HomeComponent} from './components/dashboard/home/home.component';
import {appRoutes} from "./app.routes";
import {RouterModule} from "@angular/router";
import {RocStatisticsComponent} from './components/shared/statistics/roc-statistics.component';
import {LastImageComponent} from "./components/shared/last-image/last-image.component";
import {ConsoleOutputComponent} from "./components/shared/console-output/console-output.component";
import {MatButtonModule, MatExpansionModule, MatSliderModule, MatSlideToggleModule} from "@angular/material";
import { ControlsComponent } from './components/shared/controls/controls.component';

@NgModule({
  declarations: [
    AppComponent,
    TopNavComponent,
    ConsoleOutputComponent,
    DashboardComponent,
    HomeComponent,
    LastImageComponent,
    RocStatisticsComponent,
    ControlsComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatButtonModule
  ],
  providers: [
    RocService,
    ServicesCore,
    BaseService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

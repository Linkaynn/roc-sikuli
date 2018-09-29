import { Component, OnInit } from '@angular/core';
import {ROCState} from "../../../model/roc-state";
import {StatusService} from "../../../services/status.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  state: ROCState = new ROCState();

  private intervalTime: number = 2500;
  private loadingState = false;

  constructor(private statusService: StatusService) {
    this.timeout();
  }

  private timeout() {
    setTimeout(() => {
      this.retrieveStatus();
    }, this.intervalTime);
  }

  private retrieveStatus() {
    if (this.loadingState) return;

    this.loadingState = true;
    this.statusService.getStatus().then((res) => {
      if (res.data) {
        this.state.update(res.data);
      }

      this.loadingState = false;
      this.intervalTime = 2500;
      this.timeout()
    }).catch(() => {
      this.loadingState = false;
      this.intervalTime = 5000;
      this.timeout();

      this.state.currentStatus = "OFFLINE";
    })
  }

  newSession() {
    this.statusService.newSession()
  }

}

import {Component} from '@angular/core';
import {StatusService} from "./services/status.service";
import {ROCState} from "./model/roc-state";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  state: ROCState = new ROCState();

  private interval: number;
  private loading = false;

  constructor(private statusService: StatusService) {
    this.interval = setInterval(() => this.retrieveStatus(), 500);
  }

  private retrieveStatus() {
    if (this.loading) return;

    this.loading = true;
    this.statusService.getStatus().then((res) => {
      this.loading = false;

      if (res.data) {
        this.state = ROCState.fromJSON(res.data)
      }
    }).catch(() => {
      this.loading = false;
      
      this.state.currentStatus = "OFFLINE";
      clearInterval(this.interval);
      this.interval = setInterval(() => this.retrieveStatus(), 5000);
    })
  }
}

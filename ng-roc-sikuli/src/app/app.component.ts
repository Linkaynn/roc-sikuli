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

  private intervalTime: number = 500;
  private loading = false;

  constructor(private statusService: StatusService) {
    this.timeout();
  }

  private timeout() {
    setTimeout(() => this.retrieveStatus(), this.intervalTime);
  }

  private retrieveStatus() {
    if (this.loading) return;

    this.loading = true;
    this.statusService.getStatus().then((res) => {
      this.loading = false;

      if (res.data) {
        this.state.update(res.data);
      }

      this.intervalTime = 500;
      this.timeout()
    }).catch(() => {
      this.loading = false;

      this.state.currentStatus = "OFFLINE";

      this.intervalTime = 5000;
      this.timeout()
    })
  }
}

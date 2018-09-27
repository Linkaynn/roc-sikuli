import {Component} from '@angular/core';
import {StatusService} from "./services/status.service";
import {ROCState} from "./model/roc-state";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  state: ROCState;

  constructor(private statusService: StatusService) {
    setInterval(() => this.retrieveStatus(), 500);
  }

  private retrieveStatus() {
    this.statusService.getStatus().then((res) => {
      if (res.data) {
        this.state = ROCState.fromJSON(res.data)
      }
    })
  }
}

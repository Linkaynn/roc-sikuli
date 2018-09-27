import {Component} from '@angular/core';
import {StatusService} from "./services/status.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  log: string = "";

  constructor(private statusService: StatusService) {
    statusService.getStatus().then((res) => {
      console.log(res)
    })
  }

}

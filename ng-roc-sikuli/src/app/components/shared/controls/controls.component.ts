import {Component, Input, OnInit} from '@angular/core';
import {RocService} from "../../../services/roc.service";
import {ROCState, Status} from "../../../model/roc-state";

@Component({
  selector: 'app-controls',
  templateUrl: './controls.component.html',
  styleUrls: ['./controls.component.scss']
})
export class ControlsComponent {

  status = Status;

  @Input() state: ROCState;

  constructor(private rocService: RocService) {
  }

  changeStatus(status : Status, checked: boolean) {
    this.rocService.changeStatus(status, checked).then(() => this.state.currentDoing[status] = checked).catch((err) => console.error(err))
  }

  clickOnVerify() {
    this.rocService.clickVerify().then(() => {}).catch((err) => console.error(err))
  }
}

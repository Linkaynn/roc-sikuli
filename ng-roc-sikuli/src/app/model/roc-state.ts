import {ROCState} from "./roc-state";

export class ROCState {
  currentStatus : string = "OFFLINE";
  logLines: string[] = [];

  update(json) {
    this.currentStatus = json.currentStatus;
    this.logLines = this.logLines.concat(json.logLines.filter(value => !this.logLines.includes(value)));
  }
}

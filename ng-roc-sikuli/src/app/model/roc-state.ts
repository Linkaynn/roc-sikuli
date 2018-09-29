export class ROCstatistics {
  exploredTimes: number  = 0;
}

export class ROCState {
  currentStatus : string = "OFFLINE";
  statistics: ROCstatistics = new ROCstatistics();
  logLines: string[] = [];

  update(json) {
    this.currentStatus = json.currentStatus;
    this.statistics = Object.assign(new ROCstatistics(), json.statistics);
    this.logLines = this.logLines.concat(json.logLines.filter(value => !this.logLines.includes(value)));
  }
}

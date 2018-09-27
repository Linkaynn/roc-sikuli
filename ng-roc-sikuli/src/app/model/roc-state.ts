class Statistics {
  exploredTimes: number;
}

export class ROCState {
  currentStatus : string = "OFFLINE";
  statistics: Statistics = new Statistics();
  logLines: string[] = [];

  update(json) {
    this.currentStatus = json.currentStatus;
    this.statistics = Object.assign(new Statistics(), json.statistics);
    this.logLines = this.logLines.concat(json.logLines.filter(value => !this.logLines.includes(value)));
  }
}

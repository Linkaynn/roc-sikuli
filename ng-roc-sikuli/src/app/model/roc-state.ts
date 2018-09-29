export enum Status {
  OFFLINE = "OFFLINE",
  IDLE = "IDLE",
  EXPLORING = "EXPLORING",
  ANTIBOT_PRESENT = "ANTIBOT_PRESENT",
  NO_SESSION = "NO_SESSION",
  OFF = "OFF"
}

export class RocStatistics {
  exploredTimes: number = 0;
}

export class EnvironmentInfo {
  screenWidth: number = NaN;
  screenHeight: number = NaN;
}

export class ROCState {
  currentStatus: string = "OFFLINE";
  statistics: RocStatistics = new RocStatistics();
  environmentInfo: EnvironmentInfo = new EnvironmentInfo();
  logLines: string[] = [];

  update(json) {
    this.currentStatus = json.currentStatus;

    this.environmentInfo = Object.assign(new EnvironmentInfo(), json.environmentInfo);
    this.statistics = Object.assign(new RocStatistics(), json.statistics);

    this.logLines = this.logLines.concat(json.logLines.filter(value => !this.logLines.includes(value)));
  }

  botVerificationIsPresent() {
    return this.currentStatus == Status.ANTIBOT_PRESENT;
  }

  isOffline() {
    return this.currentStatus == Status.OFFLINE;
  }

  withoutSession() {
    return this.currentStatus == Status.NO_SESSION;
  }
}

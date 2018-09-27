export class ROCState {
  currentStatus : string = "OFFLINE";
  logLines: string[] = [];

  static fromJSON(json) {
    return Object.assign(new ROCState(), json);
  }
}

export class ROCState {
  currentStatus : string;
  logLines: string[];

  static fromJSON(json) {
    return Object.assign(new ROCState(), json);
  }
}

import {Injectable} from '@angular/core';
import {BaseService} from "../helpers/base-service/base-service";
import {ServicesCore} from "../helpers/base-service/service-core-provider";

@Injectable()
export class StatusService extends BaseService {

  constructor(protected core: ServicesCore) {
    super(core);
  }

  getStatus() {
    return this.get("status");
  }

  getImage(): Promise<Blob> {
    return this.getFile("last-image");
  }

  newSession() {
    this.get("input", [{action: "new_session"}])
  }
}

import {Injectable} from '@angular/core';
import {BaseService} from "../helpers/base-service/base-service";
import {ServicesCore} from "../helpers/base-service/service-core-provider";

@Injectable()
export class RocService extends BaseService {

  constructor(protected core: ServicesCore) {
    super(core);
  }

  getStatus() {
    return this.get("status");
  }

  getImage(): Promise<Blob> {
    return this.getFile("last-image");
  }

  clickConfirm() {
    return this.get("input", [{action: "click_confirm"}])
  }

  movePiece(pos: number) {
    let params : any = [ { action: "move_piece" }, { pos: pos } ];

    return this.get("input", params)
  }
}
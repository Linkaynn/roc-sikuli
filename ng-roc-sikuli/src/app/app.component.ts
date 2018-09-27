import {Component} from '@angular/core';
import {StatusService} from "./services/status.service";
import {ROCState} from "./model/roc-state";
import {DomSanitizer} from "@angular/platform-browser";
import {ImageHelper} from "./helpers/image-helper";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  state: ROCState = new ROCState();

  lastImage: any;

  private intervalTime: number = 2500;
  private loading = false;

  constructor(private statusService: StatusService, private sanitizer: DomSanitizer) {
    this.timeout();
  }

  private timeout() {
    setTimeout(() => this.retrieveStatus(), this.intervalTime);
  }

  private retrieveStatus() {
    if (this.loading) return;

    this.loading = true;
    this.statusService.getStatus().then((res) => {

      if (res.data) {
        this.state.update(res.data);

        this.retrieveLastImage();
      }

      this.intervalTime = 2500;
      this.timeout()
    }).catch(() => {
      this.loading = false;

      this.state.currentStatus = "OFFLINE";

      this.intervalTime = 5000;
      this.timeout()
    })
  }

  private retrieveLastImage() {
    this.statusService.getImage().then((value: Blob) => {
      this.loading = false;
      this.createImageFromBlob(value);
    }).catch((err) => {
      this.loading = false;
      console.error(err);
    })
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.lastImage = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

}

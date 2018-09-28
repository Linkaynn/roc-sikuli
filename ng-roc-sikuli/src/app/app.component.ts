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
  private loadingState = false;
  private loadingImage = false;

  constructor(private statusService: StatusService, private sanitizer: DomSanitizer) {
    this.timeout();
  }

  private timeout() {
    setTimeout(() => {
      if (this.loadingImage || this.loadingState) return;

      this.retrieveStatus();
      this.retrieveLastImage();
    }, this.intervalTime);
  }

  private retrieveStatus() {
    if (this.loadingState) return;

    this.loadingState = true;
    this.statusService.getStatus().then((res) => {
      if (res.data) {
        this.state.update(res.data);
      }

      this.loadingState = false;
      this.intervalTime = 2500;
      this.timeout()
    }).catch(() => {
      this.loadingState = false;
      this.intervalTime = 5000;
      this.timeout();

      this.state.currentStatus = "OFFLINE";
    })
  }

  private retrieveLastImage() {
    if (this.loadingImage) return;

    this.loadingImage = true;

    this.statusService.getImage().then((value: Blob) => {
      this.createImageFromBlob(value);

      this.loadingImage = false;
      this.intervalTime = 2500;
      this.timeout()
    }).catch((err) => {
      this.loadingImage = false;
      this.intervalTime = 5000;
      this.timeout();

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

  newSession() {
    this.statusService.newSession()
  }
}

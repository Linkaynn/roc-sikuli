import {Component, Input, OnInit} from '@angular/core';
import {StatusService} from "../../../services/status.service";
import {ROCState} from "../../../model/roc-state";

@Component({
  selector: 'app-last-image',
  templateUrl: './last-image.component.html',
  styleUrls: ['./last-image.component.scss']
})
export class LastImageComponent {

  @Input() state : ROCState;

  lastImage: any;

  private intervalTime: number = 2500;
  private loadingImage = false;

  constructor(private statusService: StatusService) {
    this.timeout();
  }

  private timeout() {
    setTimeout(() => {
      this.retrieveLastImage();
    }, this.intervalTime);
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
}

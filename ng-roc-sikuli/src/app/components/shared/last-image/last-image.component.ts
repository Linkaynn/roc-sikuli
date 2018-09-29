import {Component, Input} from '@angular/core';
import {RocService} from "../../../services/roc.service";
import {ROCState} from "../../../model/roc-state";

@Component({
  selector: 'app-last-image',
  templateUrl: './last-image.component.html',
  styleUrls: ['./last-image.component.scss']
})
export class LastImageComponent {

  @Input() state: ROCState;

  /**
   * Screen image
   */
  currentScreenImage: any;

  /**
   * Next position of image
   */
  puzzlePieceXPos: number = 0;

  intervalTime: number = 2500;
  loadingImage = false;


  constructor(private rocService: RocService) {
    this.timeout();
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.currentScreenImage = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  private timeout() {
    setTimeout(() => {
      this.retrieveLastImage();
    }, this.intervalTime);
  }

  private retrieveLastImage() {
    if (this.loadingImage) return;

    this.loadingImage = true;

    this.rocService.getImage().then((value: Blob) => {
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

  movePiece() {
    let resetPiece = () => { this.puzzlePieceXPos = 0 };

    this.rocService.movePiece(this.puzzlePieceXPos).then(resetPiece).catch(resetPiece)
  }
}

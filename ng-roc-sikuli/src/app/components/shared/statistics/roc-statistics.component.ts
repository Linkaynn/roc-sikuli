import {Component, Input, OnInit} from '@angular/core';
import {ROCstatistics} from "../../../model/roc-state";

@Component({
  selector: 'app-roc-statistics',
  templateUrl: './roc-statistics.component.html',
  styleUrls: ['./roc-statistics.component.scss']
})
export class RocStatisticsComponent {

  @Input() statistics : ROCstatistics = new ROCstatistics();

  constructor() { }

}

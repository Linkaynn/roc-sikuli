import {Component, Input} from '@angular/core';
import {RocStatistics} from "../../../model/roc-state";

@Component({
  selector: 'app-roc-statistics',
  templateUrl: './roc-statistics.component.html',
  styleUrls: ['./roc-statistics.component.scss']
})
export class RocStatisticsComponent {

  @Input() statistics: RocStatistics = new RocStatistics();

  constructor() {
  }

}

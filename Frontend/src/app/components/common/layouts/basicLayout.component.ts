import { Component } from '@angular/core';
import { detectBody } from '../../../app.helper';

declare var jQuery: any;

@Component({
  selector: 'basic',
  templateUrl: 'basicLayout.template.html',
  host: {
    '(window:resize)': 'onResize()'
  }
})
export class BasicLayoutComponent {

  constructor() {
  }

  public ngOnInit(): any {
    detectBody();
  }

  public onResize() {
    detectBody();
  }
}

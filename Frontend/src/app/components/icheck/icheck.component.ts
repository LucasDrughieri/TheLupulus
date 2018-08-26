import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'icheck',
  templateUrl: './icheck.component.html',
  styleUrls: ['./icheck.component.scss']
})
export class ICheckComponent implements OnInit {

  @Input() checked: boolean = false;
  @Input() checkAtLeft: boolean = true;
  @Input() disabled: boolean = false;

  @Output() checkedChange = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit() {
  }

  onClick(){
    if(this.disabled) return;
    
    this.checked = !this.checked;
    this.checkedChange.emit(this.checked);
  }

}

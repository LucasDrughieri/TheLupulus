import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Ng2ModalConfig } from "./ng2modal-config";
declare var $: any;

@Component({
  selector: 'ng2-modal',
  templateUrl: './ng2modal.component.html',
  styleUrls: ['./ng2modal.component.scss']
})
export class Ng2ModalComponent implements OnInit {

  @Input() config: Ng2ModalConfig;
  @Output() close = new EventEmitter<any>();
  @Output() accept = new EventEmitter<any>();
  @Output() btn1 = new EventEmitter<any>();
  @Output() btn2 = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  show(acceptButtonDisabled){
    setTimeout(() => {
      $('#' + this.config.id).modal('toggle');

      if(acceptButtonDisabled == true){
        $('#btnSave').attr('disabled', 'disabled');
      }
      else{
        $('#btnSave').removeAttr('disabled');
      }
    });
  }

  hide(){
    setTimeout(() => {
      $('#' + this.config.id).modal('toggle');
    });
  }

  closeEvent($event){
    $event.stopPropagation();
    setTimeout(() => {
      $('#' + this.config.id).modal('toggle');
    });
    this.close.emit();
  }

  acceptEvent($event){
    $event.stopPropagation();
    this.accept.emit();
  }

  onBtn1Click(){
    this.btn1.emit();
  }

  onBtn2Click(){
    this.btn2.emit();
  }
}
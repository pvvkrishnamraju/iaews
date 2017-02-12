import {
  Component, OnDestroy
} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Observer} from 'rxjs/Observer';
import {DialogWrapperComponent} from "./dialog-wrapper.component";
import {DialogService} from "./dialog.service";

@Component({
  selector: 'pagination'
})
export abstract class DialogComponent implements OnDestroy {

  /**
   * Observer to return result from dialog
   */
  private observer: Observer<any>;
  /**
   * Dialog result
   * @type {any}
   */
  protected result: any;

  /**
   * Dialog wrapper (modal placeholder)
   */
  wrapper: DialogWrapperComponent;

  /**
   * Constructor
   * @param {DialogService} dialogService - instance of DialogService
   */
  constructor(protected dialogService: DialogService) {}

  /**
   *
   * @param {any} data
   * @return {Observable<any>}
   */
  fillData(data:any = {}): Observable<any> {
    let keys = Object.keys(data);
    for(let i=0, length=keys.length; i<length; i++) {
      let key = keys[i];
      this[key] = data[key];
    }
    return Observable.create((observer:Observer<any>)=>{
      this.observer = observer;
      return ()=>{
        this.close();
      }
    });
  }

  /**
   * Closes dialog
   */
  close():void {
    this.dialogService.removeDialog(this);
  }

  /**
   * OnDestroy handler
   */
  ngOnDestroy(): void {
    if(this.observer) {
      this.observer.next(this.result);
    }
  }
}

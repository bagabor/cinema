import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { MessageService } from 'primeng/primeng';
import { LoginDialogModul } from '../login-dialog/login-dialog.module';
import { HeaderComponent } from './header.component';

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    LoginDialogModul
  ],
  exports: [HeaderComponent],
  providers: [MessageService],
  bootstrap: [HeaderComponent]
})
export class HeaderModul { }
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SidebarComponent } from './components/admin/sidebar/sidebar.component';
import { HomeComponent } from './components/client/home/home.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive, SidebarComponent, HomeComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {

  title = "get dressed"
  constructor(private http: HttpClient, private modalService: NgbModal) { }

  public open(modal: any): void {
    this.modalService.open(modal);
  }

}
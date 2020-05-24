import { Component, OnInit } from '@angular/core';
import { EmartAuthService } from '../auth/emart-auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private authService: EmartAuthService) { }

  ngOnInit(): void {
     this.authService.logout();
  }

}

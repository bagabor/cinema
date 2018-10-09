import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/primeng';
import { CrudService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private dateFormatCode = 'en-US';

  movies: Movie[];
  selectedMovie: Movie;

  screeningTimes: Screening[];
  originalScreeningTimes = new Array<any>();
  selectedScreening: Screening;

  numberOfTickets: SelectItem[];
  selectedTicketNumber: Number;

  constructor(private apiService: CrudService, private messageService: MessageService) {
  }

  ngOnInit() {
    this.getMovies();
    this.ticketLoader();
  }

  public getMovies() {
    this.apiService.getMovies().subscribe((data: Array<Movie>) => {
      this.movies = data;
    });
  }

  onChange(event) {
    if (this.selectedMovie !== null) {
      this.screeningTimes = this.selectedMovie.screenings.map(e => {
        return { id: e['id'], time: this.dateConverter(e) };
      });
    }
  }

  dateConverter(e: any){
    const pipe = new DatePipe(this.dateFormatCode);
    this.originalScreeningTimes.push(e);
    return pipe.transform(e['time'], 'medium');
  }

  handleClick(event) {
    let reservation: Reservation;
    reservation = {
      id: null,
      numberOfTickets: this.selectedTicketNumber,
      user: { id: 1 },
      screening: this.originalScreeningTimes.filter(e => e['id'] === this.selectedScreening['id'])[0]
    }
    this.apiService.reverse(reservation).subscribe(
      response => { this.messageService.add({ key: 'toaster', severity: 'success', summary: 'Success Message', detail: 'Tickets reserved.' }); },
      err => { this.messageService.add({ key: 'toaster', severity: 'error', summary: 'Error Message', detail: 'No enough seats or wrong booking.' }); }
    );
  }

  private ticketLoader() {
    this.numberOfTickets = [
      { label: '1', value: 1 },
      { label: '2', value: 2 },
      { label: '3', value: 3 }
    ];
  }

}
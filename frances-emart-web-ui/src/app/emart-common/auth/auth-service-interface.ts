import { Observable } from 'rxjs';
import { EmartUser } from '../models/emart-user';

export interface AuthService {
   login(usename: string, password: string): Observable<EmartUser>;
   logout();
}

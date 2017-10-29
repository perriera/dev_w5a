/**
 * Created by perry on 2017-07-25.
 */

export interface Document { _id: string; }
import { IUserDocument } from './IUser';

export interface ISession {
    sid: string;
    user: IUserDocument;
    expires: number;
}

export interface ISessionDocument extends Document, ISession {
}


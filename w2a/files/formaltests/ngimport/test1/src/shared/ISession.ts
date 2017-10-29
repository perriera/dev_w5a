/**
 * Created by perry on 2017-07-25.
 */

import { Document } from 'mongoose';
import { IUserDocument } from './IUser';

export interface ISession {
    sid: string;
    user: IUserDocument;
    expires: number;
}

export interface ISessionDocument extends Document, ISession {
}

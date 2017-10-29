/**
 * Created by perry on 2017-07-24.
 */

export class Cursor<T> {

    public cache: T[];

    constructor(public pointer: number,
                public limit: number) {
    }

    pageUp() {
        this.pointer -= this.limit;
    }

    pageDown() {
        this.pointer += this.limit;
    }

}


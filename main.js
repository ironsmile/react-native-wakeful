import { NativeModules, Platform } from 'react-native';

class ImplementedWakeful {
    constructor() {
      this.RNWakeful = NativeModules.RNWakeful;
    }

    isHeld() {
        return this.RNWakeful.isHeld();
    }

    acquire() {
        return this.RNWakeful.acquire();
    }

    release() {
        return this.RNWakeful.release();
    }
}

class DummyWakeful {
    constructor() {
      this.locksHeld = false;
    }
    acquire() {
        if (this.isHeld()) {
            return;
        }
        this.locksHeld = true;
    }
    release() {
        if (!this.isHeld()) {
            return;
        }
        this.locksHeld = false;
    }
    isHeld() {
        return this.locksHeld;
    }
}

let Wakeful;

if (Platform.OS === 'android') {
    Wakeful = ImplementedWakeful;
} else {
    Wakeful = DummyWakeful;
}

module.exports = Wakeful;

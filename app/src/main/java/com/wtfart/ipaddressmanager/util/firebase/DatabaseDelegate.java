package com.wtfart.ipaddressmanager.util.firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by mickeycj on 11/15/2017.
 */
public class DatabaseDelegate {

    private static ChildEventListener sUsersChildEventListener;
    private static ChildEventListener sIpAddressRangesChildEventListener;

    protected static void retrieveIpAddresses(final String uid) {
        sUsersChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddresses(uid, dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Database.updateIpAddresses(uid, dataSnapshot, Database.REVOKE_ACTION);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddresses(uid, dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddresses(uid, dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        Database.getUsersReference().addChildEventListener(sUsersChildEventListener);
    }

    protected static void retrieveIpAddressRanges() {
        sIpAddressRangesChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddressesRanges(dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Database.updateIpAddressesRanges(dataSnapshot, Database.REVOKE_ACTION);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddressesRanges(dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Database.updateIpAddressesRanges(dataSnapshot, Database.RETRIEVE_ACTION);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        Database.getIpAddressRangesReference().addChildEventListener(sIpAddressRangesChildEventListener);
    }

    protected static void removeChildEventListeners() {
        Database.getUsersReference().removeEventListener(sUsersChildEventListener);
        Database.getIpAddressRangesReference().removeEventListener(sIpAddressRangesChildEventListener);
    }
}

package com.hfad.homework4geninf;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public List<BlogMessage> messages = new ArrayList<>();


    public interface DataStatus{
        void DataIsLoaded(List<BlogMessage> messages);
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("blogMessages");
    }
    public void deleteMessage(String messageId, final DataStatus dataStatus){
        databaseReference.child(messageId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               dataStatus.DataIsDeleted();
            }
        });
    }
    public void readMessages(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            messages.clear();
            for(DataSnapshot snap:dataSnapshot.getChildren()){
               BlogMessage blogMessage = snap.getValue(BlogMessage.class);
               messages.add(blogMessage);
            }
                dataStatus.DataIsLoaded(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

package thanhlongbanh8997.englishforeverybody.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import thanhlongbanh8997.englishforeverybody.R;

import static android.app.Activity.RESULT_OK;

public class InfomationFragment extends Fragment {
    View v;
    ImageView imgUser;
    Button chooseImageFromGallery;
    Button usingCamera;

    public InfomationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_info,container,false);

        imgUser = v.findViewById(R.id.imgUser);
        chooseImageFromGallery = v.findViewById(R.id.chooseImageFromGallery);
        usingCamera = v.findViewById(R.id.usingCamera);

        ImageGallery();
        UsingCamera();





        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgUser.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgUser.setImageURI(selectedImage);
                }
                break;
        }

    }

    public void ImageGallery(){
        chooseImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
            }
        });
    }

    public void UsingCamera(){
        usingCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);//zero can be replaced with any action code
            }
        });

    }
}

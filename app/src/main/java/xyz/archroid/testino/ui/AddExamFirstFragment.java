package xyz.archroid.testino.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.Objects;

import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Data.UploadImageController;
import xyz.archroid.testino.R;

import static android.app.Activity.RESULT_OK;


public class AddExamFirstFragment extends Fragment {

    private MaterialCardView cardView_image;

    private TestinoAPI.uploadImageCallback uploadImageCallback;
    private static final int GALARY_PICK = 1;

    private ImageView imageView_icon;

    public AddExamFirstFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_exam_first, container, false);

        cardView_image = view.findViewById(R.id.cardView_image_exam_activity);
        imageView_icon = view.findViewById(R.id.imageView_icon_exam_activity);

        uploadImageCallback = new TestinoAPI.uploadImageCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String status, String error) {

            }

            @Override
            public void onFailure(String cause) {

            }
        };

        cardView_image.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "SELECT IMAGE"), GALARY_PICK);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALARY_PICK && resultCode == RESULT_OK) {
            assert data != null;
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(2, 1)
                    .start(Objects.requireNonNull(getContext()), this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                assert result != null;
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());

                UploadImageController uploadImageController = new UploadImageController(uploadImageCallback);
                uploadImageController.start(file, "ExamIcon");

                imageView_icon.setImageURI(resultUri);

            }
        }
    }
}
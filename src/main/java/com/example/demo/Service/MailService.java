package com.example.demo.Service;

import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    public void sendVerificationMail(

            String toEmail,

            String link

    ) {

        try {

            OkHttpClient client =
                    new OkHttpClient();

            MediaType mediaType =
                    MediaType.parse(
                            "application/json"
                    );

            String json =
                    """
                    {
                      "from": "onboarding@resend.dev",
                      "to": ["%s"],
                      "subject": "Verify TalentNest Account",
                      "html": "<h2>Email Verification</h2><p>Click below link to verify your account:</p><a href='%s'>Verify Account</a>"
                    }
                    """.formatted(
                            toEmail,
                            link
                    );

            RequestBody body =
                    RequestBody.create(
                            json,
                            mediaType
                    );

            Request request =
                    new Request.Builder()
                            .url(
                                    "https://api.resend.com/emails"
                            )
                            .post(body)
                            .addHeader(
                                    "Authorization",
                                    "Bearer " +
                                            System.getenv(
                                                    "RESEND_API_KEY"
                                            )
                            )
                            .addHeader(
                                    "Content-Type",
                                    "application/json"
                            )
                            .build();

            Response response =
                    client.newCall(request)
                            .execute();

            System.out.println(
                    response.body().string()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
package graphic;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final int vaoId;
    private final ArrayList<Integer> vboIds;
    private final int vertexCount;
    private Texture texture;

    public Mesh(float[] vertices, byte[] indices){
        vboIds = new ArrayList<>();

        FloatBuffer verticesBuffer = null;
        ByteBuffer indicesBuffer = null;

        try{
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            indicesBuffer = MemoryUtil.memAlloc(indices.length);
            indicesBuffer.put(indices).flip();

            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            int vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);
            vboIds.add(vboId);

            vboId = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }finally{
            if(verticesBuffer != null)
                MemoryUtil.memFree(verticesBuffer);
            if(indicesBuffer != null)
                MemoryUtil.memFree(indicesBuffer);
        }
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }

    public void setTextCoord(float[] textCoord){
        FloatBuffer textCoordBuffer = null;
        try{
            textCoordBuffer = MemoryUtil.memAllocFloat(textCoord.length);
            textCoordBuffer.put(textCoord).flip();

            glBindVertexArray(vaoId);
    
            int vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);
            vboIds.add(vboId);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }finally{
            if(textCoordBuffer != null)
                MemoryUtil.memFree(textCoordBuffer);
        }
    }

    public void render(){
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture.getTextureId());

        glBindVertexArray(vaoId);

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_BYTE, 0);

        glBindVertexArray(0);
    }

    public void cleanUp(){
        glDisableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for (Integer vboId : vboIds) {
            glDeleteBuffers(vboId);
        }

        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}
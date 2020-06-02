package graphic;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    protected final int vaoId;
    protected final ArrayList<Integer> vboIds;
    protected int vertexCount;
    private Texture texture;

    public Mesh(){
        vboIds = new ArrayList<>();
        vaoId = glGenVertexArrays();
    }

    public Mesh(float[] vertices, int[] indices){
        this();
        setVertices(vertices);
        setIndices(indices);
    }

    public void setVertices(float[] vertices){
        FloatBuffer verticesBuffer = null;
        try{
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            glBindVertexArray(vaoId);

            int vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);
            vboIds.add(vboId);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }finally{
            if(verticesBuffer != null)
                MemoryUtil.memFree(verticesBuffer);
        }
    }

    public void setIndices(int[] indices){
        IntBuffer indicesBuffer = null;

        try{
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();

            vertexCount = indices.length;

            glBindVertexArray(vaoId);

            int vboId = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }finally{
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

    protected void initRender(){
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
        glBindVertexArray(vaoId);
    }

    public void render(){
        initRender();

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        endRender();
    }

    protected void endRender(){
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